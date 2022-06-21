#import "PreviewViewController.h"
#import <SDWebImage/SDWebImage.h>

@interface PreviewViewController ()

@property (nonatomic, strong) SDAnimatedImageView *imageView;

@end

@implementation PreviewViewController

- (instancetype)initWithURL:(NSString *)url previewImageSize:(NSArray *)previewImageSize {
    self = [super init];
    if (self) {
        _previewImageSize = previewImageSize;
        _url = url;
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];

    self.imageView = [SDAnimatedImageView new];
    NSURL* url = [NSURL URLWithString:_url];

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
            CGFloat width = self.view.bounds.size.width;
            CGFloat height = image.size.height * (width / image.size.width);

            [self setPreferredContentSize:CGSizeMake(width, height)];
        }
    }];
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
