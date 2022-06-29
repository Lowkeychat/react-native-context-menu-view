#import "PreviewViewController.h"
#import <SDWebImage/SDWebImage.h>

@interface PreviewViewController ()

@property (nonatomic, strong) SDAnimatedImageView *imageView;

@end

@implementation PreviewViewController

- (instancetype)initWithURL:(NSString *)url previewSize:(NSArray *)previewSize {
    self = [super init];
    if (self) {
        _url = url;
        _previewSize = previewSize;
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    self.imageView = [SDAnimatedImageView new];
    NSURL* url = [NSURL URLWithString:_url];

    if ([_previewSize count] > 1) {
        [self calculatePreferredSizeWithWidth:[_previewSize[0] floatValue] height:[_previewSize[1] floatValue]];
    }
    self.imageView.sd_imageTransition = SDWebImageTransition.fadeTransition;
    self.imageView.sd_imageTransition.duration = 0.25;
    self.imageView.shouldIncrementalLoad = false;
    self.imageView.contentMode = UIViewContentModeScaleAspectFit;
    self.imageView.clipsToBounds = true;

    [self.view addSubview:self.imageView];

    [NSLayoutConstraint activateConstraints:@[
          [self.imageView.leadingAnchor constraintEqualToAnchor:self.view.leadingAnchor],
          [self.imageView.trailingAnchor constraintEqualToAnchor:self.view.trailingAnchor],
          [self.imageView.topAnchor constraintEqualToAnchor:self.view.topAnchor],
          [self.imageView.bottomAnchor constraintEqualToAnchor:self.view.bottomAnchor]
    ]];

    [self.imageView sd_setImageWithURL:url placeholderImage:nil options:SDWebImageProgressiveLoad completed:^(UIImage * _Nullable image, NSError * _Nullable error, SDImageCacheType cacheType, NSURL * _Nullable imageURL) {
        if (image) {
            [self calculatePreferredSizeWithWidth:image.size.width height:image.size.height];
        }
    }];
}

- (void) calculatePreferredSizeWithWidth:(CGFloat) width height:(CGFloat)height {
    CGFloat boundsWidth = self.view.bounds.size.width;
    CGFloat nextHeight = height * (boundsWidth / width);

    [self setPreferredContentSize:CGSizeMake(boundsWidth, nextHeight)];
}

- (void)viewDidLayoutSubviews {
    [super viewDidLayoutSubviews];
    self.imageView.frame = self.view.bounds;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
}

@end
