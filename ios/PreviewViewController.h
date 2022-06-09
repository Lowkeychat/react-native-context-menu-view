@import UIKit;

@interface PreviewViewController : UIViewController
    @property (nonnull, nonatomic, copy) NSString* url;

    - (instancetype)initWithURL:(NSString *)url;
@end
